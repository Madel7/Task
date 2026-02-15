public class Main {
    public static void main(String[] args) throws InterruptedException {
        Repo repo = new Repo();
        Service service = new Service(repo);
        View view = new View();
        Controller controller = new Controller(service, view);

        controller.start();
    }
}
