package register.sk.tuke.register;

public class RegisterException extends Exception {
    @Override
    public String toString() {
        return "Person already exists";
    }
}
