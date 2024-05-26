package main;

import java.io.*;
import java.util.Arrays;

public class Main {

    private static final String PATH = "C:\\Users\\user\\Desktop\\SIT\\";

    public static void main(String[] args) throws IOException {
        System.out.println("#### Java IO ####");
//        demoFileInputOutputStream();
//        demoByteArrayInputOutputStream();
//        demoBufferedInputOutputStream();
//        demoDataInputOutputStream();
//        demoObjectInputOutputStream();
        demoFileReaderWriter();
    }

    public static void demoFileInputOutputStream() {
        System.out.println("## demoFileInputStream ##");
        String fileName = PATH + "byte.dat";
        try (FileOutputStream fo = new FileOutputStream(fileName)) {
            fo.write((byte) 50);
            fo.write(new byte[] {67,68,69,70,70,70});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int len = fis.available();
            System.out.println("available: " + len);
            byte[] out = fis.readNBytes(len);
            System.out.println("Contents: " + Arrays.toString(out));
            System.out.println("Done reading.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int i;
            while ((i = fis.read()) != -1) {
                System.out.print(" " + (byte) i);
            }
            System.out.println();
            System.out.println("Done second reading.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void demoByteArrayInputOutputStream() {
        System.out.println("## demoByteArrayInputOutputStream ##");
        String fileName = PATH + "byteArray.dat";
        try (ByteArrayOutputStream bo = new ByteArrayOutputStream()) {
            bo.write((byte) 50);
            bo.write((byte) 96);
            byte[] out = bo.toByteArray();
            System.out.println("Writing: " + Arrays.toString(out));
            bo.write((byte) 97);
            bo.reset();
            bo.write(new byte[] {67, 68, 69, 70, 70, 70});
            out = bo.toByteArray();
            // also write output to file.
            try (FileOutputStream fo = new FileOutputStream(fileName)) {
                bo.writeTo(fo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Reading from an array of bytes.");
        byte[] input = {70,71,72,73,74,75,76,77,77,77};
        try (ByteArrayInputStream bi = new ByteArrayInputStream(input)) {
            int len = bi.available();
            System.out.println("available: " + len);
            byte[] out = bi.readNBytes(len);
            System.out.println("Contents: " + Arrays.toString(out));
            System.out.println("Done reading and try it all over again.");
            bi.reset();
            int i;
            while ((i = bi.read()) != -1) {
                System.out.print(" " + (byte) i);
            }
            System.out.println();
            System.out.println("Done second reading.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void demoBufferedInputOutputStream() {
        System.out.println("## demoBufferedInputOutputStream ##");
        String fileName = PATH + "byte.dat";
        try (FileOutputStream fo = new FileOutputStream(fileName);
             BufferedOutputStream bo = new BufferedOutputStream(fo, 1000)) {
            bo.write((byte) 50);
            bo.write(new byte[] {67,68,69,70,70,70});
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedInputStream bi = new BufferedInputStream(fis, 1000)) {
            int len = fis.available();
            System.out.println("available: " + len);
            byte[] out = fis.readNBytes(len);
            System.out.println("Contents: " + Arrays.toString(out));
            System.out.println("Done reading.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedInputStream bi = new BufferedInputStream(fis, 1000)) {
            int i;
            while ((i = fis.read()) != -1) {
                System.out.print(" " + (byte) i);
            }
            System.out.println();
            System.out.println("Done second reading.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demoDataInputOutputStream() {
        System.out.println("## demoDataInputOutputStream ##");
        String fileName = PATH + "primitives.dat";
        try (FileOutputStream fo = new FileOutputStream(fileName);
             BufferedOutputStream bo = new BufferedOutputStream(fo, 1000);
             DataOutputStream dos = new DataOutputStream(bo)) {
            byte[] bytes = {67,68,69,70,70,70};
            dos.write(bytes.length);
            dos.write(bytes);
            dos.writeUTF("This is a test.");
            dos.writeBoolean(true);
            dos.writeDouble(32.42342);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedInputStream bi = new BufferedInputStream(fis, 1000);
             DataInputStream dis = new DataInputStream(bi)) {
            byte[] out = dis.readNBytes(dis.read());
            System.out.println("bytes: " + Arrays.toString(out));
            System.out.println("String: " + dis.readUTF());
            System.out.println("Boolean: " + dis.readBoolean());
            System.out.println("Double: " + dis.readDouble());
            System.out.println("Done reading.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void demoObjectInputOutputStream() {
        System.out.println("## demoObjectInputOutputStream ##");
        String fileName = PATH + "object.dat";
        Person p1 = new Person(99, "First");
        p1.setLuckNumber(111);
        Person p2 = new Person(888, "Second");
        p2.setLuckNumber(222);
        Person p3 = new Person(777, "Third");
        p3.setLuckNumber(333);
        p1.addFriend(p2);
        p1.addFriend(p3);
        p2.addFriend(p3);
        p3.addFriend(p1);
        System.out.println("Person: " + p1.toString());
        System.out.println("Person: " + p2.toString());
        System.out.println("Person: " + p3.toString());
        try (FileOutputStream fo = new FileOutputStream(fileName);
             BufferedOutputStream bo = new BufferedOutputStream(fo, 1000);
             ObjectOutputStream oos = new ObjectOutputStream(bo)) {
            byte[] bytes = {67,68,69,70,70,70};
            oos.write(bytes.length);
            oos.write(bytes);
            oos.writeUTF("This is a test.");
            oos.writeBoolean(true);
            oos.writeDouble(32.42342);
            oos.writeObject(p1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedInputStream bi = new BufferedInputStream(fis, 1000);
             ObjectInputStream ois = new ObjectInputStream(bi)) {
            byte[] out = ois.readNBytes(ois.read());
            System.out.println("bytes: " + Arrays.toString(out));
            System.out.println("String: " + ois.readUTF());
            System.out.println("Boolean: " + ois.readBoolean());
            System.out.println("Double: " + ois.readDouble());
            Person p = (Person) ois.readObject();
            System.out.println("Person1: " + p);
            for (var it = p.getFriends(); it.hasNext(); ) {
                System.out.println("friend: " + it.next());
            }
            System.out.println("Done reading.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void demoFileReaderWriter() throws IOException {
        System.out.println("### demoFileReader ###");
        String fileName = PATH + "char.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        try {
            bw.write("This is a line");
            bw.newLine();
            bw.write("This is another line");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
        System.out.println("Done writing.");
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println("Line: " + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}