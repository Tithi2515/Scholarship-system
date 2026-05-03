import java.util.*;

public class ScholarshipApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("═".repeat(60));
        System.out.println("🎓 GOVERNMENT SCHOLARSHIP PORTAL 2024");
        System.out.println("═".repeat(60));
        
        // Personal Details
        System.out.print("Full Name: ");
        String fullName = sc.nextLine();
        
        System.out.print("Email: ");
        String email = sc.nextLine();
        
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        
        System.out.print("Gender (male/female/other): ");
        String gender = sc.nextLine().toLowerCase();
        
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dob = sc.nextLine();
        
        // Academic & Income
        System.out.print("Annual Family Income (₹): ");
        int income = sc.nextInt();
        sc.nextLine(); // consume newline
        
        System.out.print("Marks Percentage: ");
        double marks = sc.nextDouble();
        sc.nextLine(); // consume newline
        
        System.out.print("Category (general/obc/sc/st): ");
        String category = sc.nextLine().toLowerCase();
        
        // 👉 BANK DETAILS
        System.out.println("\n🏦 BANK ACCOUNT DETAILS");
        System.out.print("Bank Name: ");
        String bankName = sc.nextLine();
        
        System.out.print("Account Holder Name: ");
        String accountHolder = sc.nextLine();
        
        System.out.print("Account Number: ");
        String accountNo = sc.nextLine();
        
        System.out.print("IFSC Code: ");
        String ifsc = sc.nextLine();
        
        System.out.print("Branch: ");
        String branch = sc.nextLine();
        
        // 👉 DOCUMENTS CHECK
        System.out.println("\n📎 UPLOADED DOCUMENTS (Y/N):");
        System.out.print("1. Aadhaar Card: ");
        String aadhaar = sc.nextLine().equalsIgnoreCase("Y") ? "Uploaded" : "Pending";
        
        System.out.print("2. Income Certificate: ");
        String incomeCert = sc.nextLine().equalsIgnoreCase("Y") ? "Uploaded" : "Pending";
        
        System.out.print("3. Caste Certificate: ");
        String casteCert = sc.nextLine().equalsIgnoreCase("Y") ? "Uploaded" : "Pending";
        
        System.out.print("4. Marks Card: ");
        String marksCard = sc.nextLine().equalsIgnoreCase("Y") ? "Uploaded" : "Pending";
        
        System.out.print("5. PAN Card: ");
        String panCard = sc.nextLine().equalsIgnoreCase("Y") ? "Uploaded" : "Pending";
        
        System.out.print("6. Bank Passbook: ");
        String bankPassbook = sc.nextLine().equalsIgnoreCase("Y") ? "Uploaded" : "Pending";
        
        // Process Eligibility
        System.out.println("\n" + "═".repeat(60));
        System.out.println("PROCESSING APPLICATION...");
        EligibilityResult result = checkEligibility(income, marks, category);
        
        // Display Complete Result
        displayResult(fullName, email, phone, gender, dob, income, marks, category, 
                     bankName, accountHolder, accountNo, ifsc, branch,
                     aadhaar, incomeCert, casteCert, marksCard, panCard, bankPassbook,
                     result);
    }
    
    // 👉 Eligibility Logic (Same as Frontend)
    static class EligibilityResult {
        boolean eligible;
        int rank;
        String reason;
        
        EligibilityResult(boolean eligible, int rank, String reason) {
            this.eligible = eligible;
            this.rank = rank;
            this.reason = reason;
        }
    }
    
    static EligibilityResult checkEligibility(int income, double marks, String category) {
        Map<String, Integer> incomeLimits = Map.of(
            "general", 250000, "obc", 300000, 
            "sc", 350000, "st", 400000
        );
        
        Map<String, Double> marksReq = Map.of(
            "general", 75.0, "obc", 70.0, 
            "sc", 65.0, "st", 60.0
        );
        
        int incomeLimit = incomeLimits.getOrDefault(category, 250000);
        double minMarks = marksReq.getOrDefault(category, 75.0);
        
        if (marks >= minMarks && income <= incomeLimit) {
            Random rand = new Random();
            int rank = rand.nextInt(1000) + 1;
            return new EligibilityResult(true, rank, "✅ Meets all criteria");
        } else {
            String reason = marks < minMarks ? 
                "Marks required: " + minMarks + "% (You: " + marks + "%)" :
                "Income limit: ₹" + String.format("%,d", incomeLimit) + " (Yours: ₹" + String.format("%,d", income) + ")";
            return new EligibilityResult(false, 0, reason);
        }
    }
    
    // 👉 Complete Result Display
    static void displayResult(String fullName, String email, String phone, String gender, 
                             String dob, int income, double marks, String category,
                             String bankName, String accountHolder, String accountNo, 
                             String ifsc, String branch,
                             String aadhaar, String incomeCert, String casteCert, 
                             String marksCard, String panCard, String bankPassbook,
                             EligibilityResult result) {
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("📊 APPLICATION RESULT");
        System.out.println("═".repeat(60));
        
        // Personal Info
        System.out.println("👤 PERSONAL DETAILS:");
        System.out.println("   Name     : " + fullName);
        System.out.println("   Email    : " + email);
        System.out.println("   Phone    : " + phone);
        System.out.println("   Gender   : " + gender.toUpperCase());
        System.out.println("   DOB      : " + dob);
        System.out.println("   Category : " + category.toUpperCase());
        System.out.printf("   Marks    : %.1f%%\n", marks);
        System.out.printf("   Income   : ₹%,d\n", income);
        
        // Bank Info
        System.out.println("\n🏦 BANK DETAILS:");
        System.out.println("   Bank     : " + bankName);
        System.out.println("   Holder   : " + accountHolder);
        System.out.println("   A/c No   : ****" + accountNo.substring(Math.max(0, accountNo.length() - 4)));
        System.out.println("   IFSC     : " + ifsc);
        System.out.println("   Branch   : " + branch);
        
        // Documents
        System.out.println("\n📎 DOCUMENTS STATUS:");
        System.out.println("   Aadhaar         : " + aadhaar);
        System.out.println("   Income Cert     : " + incomeCert);
        System.out.println("   Caste Cert      : " + casteCert);
        System.out.println("   Marks Card      : " + marksCard);
        System.out.println("   PAN Card        : " + panCard);
        System.out.println("   Bank Passbook   : " + bankPassbook);
        
        // Eligibility Result
        System.out.println("\n" + "═".repeat(60));
        if (result.eligible) {
            System.out.println("🎉 CONGRATULATIONS " + fullName.split(" ")[0].toUpperCase() + "!");
            System.out.println("✅ YOUR APPLICATION IS APPROVED!");
            System.out.println("   RANK: #" + result.rank);
            System.out.println("   STATUS: SHORTLISTED");
            System.out.println("\n💰 Scholarship will be credited to your bank account.");
        } else {
            System.out.println("❌ APPLICATION REJECTED");
            System.out.println("   Reason: " + result.reason);
            System.out.println("\n💡 Try again next year with better marks!");
        }
        System.out.println("═".repeat(60));
        System.out.println("Thank you for applying! Visit scholarship.gov.in");
    }
}