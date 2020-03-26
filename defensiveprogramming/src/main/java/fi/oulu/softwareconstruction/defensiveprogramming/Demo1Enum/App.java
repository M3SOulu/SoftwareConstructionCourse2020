package fi.oulu.softwareconstruction.defensiveprogramming.Demo1Enum;

public class App {
    enum Level {
        LOW,
        MEDIUM,
        HIGH
    }
    
    public static void main(String[] args) {
        Level level = Level.LOW;
        System.out.println(level);
        System.out.println(level.ordinal());
        
        level = Level.MEDIUM;
        System.out.println(level);
        System.out.println(level.ordinal());
        
        level = Level.HIGH;
        System.out.println(level);
        System.out.println(level.ordinal());
    }
}
