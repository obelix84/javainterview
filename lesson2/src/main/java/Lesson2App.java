import ru.gb.arraylist.MyArrayList;
import ru.gb.linkedlist.MyLinkedList;

public class Lesson2App {

    public static void main(String[] args) {
        MyArrayList<Integer> arrayList= new MyArrayList<>();

        for (int i = 0; i < 11; i++) {
            arrayList.add(i + 1);
        }

        System.out.println(arrayList);

        arrayList.add(0, 1000);
        System.out.println(arrayList);
        arrayList.remove(1);
        System.out.println(arrayList);



//        MyLinkedList<Integer> list = new MyLinkedList<>();
//        list.add(0, 1000);
//        list.print();
//        list.add(0);
//        list.add(1);
//        list.add(2);
//        list.print();
//        list.add(0, 7);
//        list.print();
//        list.add(3, 8);
//        list.print();
//        list.add(12);
//        list.print();
//        list.remove(1);
//        list.print();
//        System.out.println(list.indexOf(8));
//        System.out.println(list.indexOf(12));
//        Integer intTest = 12;
//        list.remove(intTest);
//        list.print();
//        list.add(13);
//        list.print();
//        intTest = 7;
//        list.remove(intTest);
//        list.print();
//        list.add(0, 20000);
//        list.print();
//        intTest = 2;
//        list.remove(intTest);
//        list.print();
    }
}
