package src.Domain;

public enum Status {
    OnTime{
        public String toString() { return "On Time"; }
    },
    Delayed{
        public String toString() { return "Delayed"; }
    };
    
    /** toString() method 
     * converts the default enum value's format to a lowercase String
     * @return   String
    */
    public abstract String toString() ;
}
