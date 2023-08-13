package mathutils;

class ABC  implements Runnable {
    public void run() {
        try {    Thread.sleep(100);    } catch (InterruptedException ie) {  ie.printStackTrace();}
        System.out.println("thread t1 in join state "+ Test.t1.getState());

        try {   Thread.sleep(200);   } catch (InterruptedException e) { throw new RuntimeException(e); }
    }
}

public class Test implements Runnable {
    public static Thread t1;
    public static Test obj;

    public static void main(String args[]) {
        obj = new Test();
        t1 = new Thread(obj);
        System.out.println("thread t1 in new state - " + t1.getState());
        t1.start();
        System.out.println("thread t1 in start state - " + t1.getState());
    }

    public void run() {
        ABC myObj = new ABC();
        Thread t2 = new Thread(myObj);
        System.out.println("thread t2 in new state - "+ t2.getState());
        t2.start();
        System.out.println("thread t2 in start state - " + t2.getState());
        try {   Thread.sleep(200);      } catch (InterruptedException ie) {ie.printStackTrace(); }

        System.out.println("thread t2 in sleep state - "+ t2.getState() );
        try {  t2.join();     } catch (InterruptedException ie) {ie.printStackTrace();  }
        System.out.println("thread t2 in  dead state - " + t2.getState());
    }
}