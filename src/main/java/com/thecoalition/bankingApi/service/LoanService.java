
        package com.thecoalition.bankingApi.service;

        import com.thecoalition.bankingApi.model.Account;
        import com.thecoalition.bankingApi.model.Customer;
        import com.thecoalition.bankingApi.model.Loan;
        import com.thecoalition.bankingApi.repository.AccountRepository;
        import com.thecoalition.bankingApi.repository.CustomerRepository;
        import com.thecoalition.bankingApi.repository.LoanRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.concurrent.Executors;
        import java.util.concurrent.ScheduledExecutorService;
        import java.util.concurrent.TimeUnit;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired private CustomerService customerService;

    @Autowired private AccountRepository accountRepository;
    private static final int MINIMUM_CREDIT_SCORE = 300;

    public double calculateLoanAmount(int creditScore) {
        return creditScore * 10.0;
    }

    public String lookForLoan(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        String name = customer.getFirstName()+ " " + customer.getLastName();

        Integer creditScore = customer.getCreditScore();

        if (creditScore >= MINIMUM_CREDIT_SCORE) {
            double loanAmount = calculateLoanAmount(creditScore);
            return "The customer named " + name + " can get a loan of up to " + loanAmount;
        } else {
            return "Customer does not qualify for a loan";
        }
    }

    public String applyForLoan(Long customerId, Long accountId, double loanAmount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Integer creditScore = customer.getCreditScore();

        double maxLoanAmount = calculateLoanAmount(creditScore);

        if (creditScore >= MINIMUM_CREDIT_SCORE && loanAmount <= maxLoanAmount) {
            Loan loan = new Loan();
            loan.setCustomerId(customer);
            loan.setAmount(loanAmount);
            loan.setStatus("Pending");
            loan.setAccountId(account);

            // Save the loan with a status of "Pending"
            loan = loanRepository.save(loan);

            final Loan finalLoan = loan;
            final Long finalAccountId = accountId;
            final double finalLoanAmount = loanAmount;

            String name = customer.getFirstName()+ " " + customer.getLastName();
            String accountType = account.getType().toString();

            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                finalLoan.setStatus("Approved");
                loanRepository.save(finalLoan);
                transactionService.deposit(finalAccountId, finalLoanAmount);
            }, 10, TimeUnit.SECONDS);

            return "The customer with the ID " + customerId + " and name " + name + ", and with the " + accountType.toLowerCase() +
                    " Account ID " + accountId + " is getting a loan of " + loanAmount + " in 10 seconds";
        } else if (loanAmount > maxLoanAmount) {
            return "The requested loan amount is too high. The maximum loan amount for this customer is " + maxLoanAmount;
        } else {
            return "Customer does not qualify for a loan";
        }
    }
}


