package main;

public interface StringOperations {
    String apply(String operation);

}
class NothingOperation implements StringOperations {

    @Override
    public String apply(String subString) {
        return subString;
    }
}



