import java.util.ArrayList;
import java.util.List;

public class Admin {
    private int adminId;
    private String name;
    private String email;
    private String password;


    private List<String> membershipPlans = new ArrayList<>();
    private List<Trainer> trainers = new ArrayList<>();
    
    public Admin(int adminId, String name, String email, String password) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    
    
    public int getAdminId() {
        return adminId;
    }



    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public void createMembershipPlan(String planName, double price, int duration) {
        String plan = planName + ": $" + price + " for " + duration + " months";
        membershipPlans.add(plan);
        System.out.println("Membership plan created: " + plan);
    }

    public void viewMembershipPlans() {
        for (String plan : membershipPlans) {
            System.out.println(plan);
        }
    }

    
    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
        System.out.println("Trainer added: " + trainer.getName());
    }

    public void viewTrainers() {
        for (Trainer trainer : trainers) {
            System.out.println(trainer.getName());
        }
    }
    

}
