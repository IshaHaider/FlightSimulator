package src.Domain;


public enum AirplaneClass {
    Economy{
        public String toString() { return "Economy Class"; }
    },
    Business{
        public String toString() { return "Business Class"; }
    };
    
    /** toString() method 
     * converts the default enum value's format to a lowercase String
     * @return   String
    */
    public abstract String toString() ;
}