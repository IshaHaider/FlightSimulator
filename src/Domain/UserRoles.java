package src.Domain;

public enum UserRoles {
    NormalUser{
        public String toString() { return "NormalUser"; }
    },
    RegisteredUser{
        public String toString() { return "RegisteredUser"; }
    },
    Employee{
        public String toString() { return "Employee"; }
    },
    Admin{
        public String toString() { return "Admin"; }
    };
    
    /** toString() method 
     * converts the default enum value's format to a lowercase String
     * @return   String
    */
    public abstract String toString() ;
}
