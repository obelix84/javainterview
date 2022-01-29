package lists;

public class ListApp {

    public static void main(String[] args) {
        SingleList<Integer> list = new SingleList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.print();
        //зацикливаем
        list.getNode(3).setNext(list.getNode(1));
        System.out.println(checkLoop(list));



    }
    //Задача на зацикливание
    public static boolean checkLoop(SingleList list) {
        if (list.getNode(0).getNext() == null)
            return false;

        SingleList.Node node1 = null;
        SingleList.Node node2 = null;
        boolean loop = false;
        int i = 1;
        while ((node1 = list.getNode(i)) != null) {
            int count = 0;
            System.out.println("checking: position " + i + " " +node1);
            for (int j = 0 ; j < i; j++) {
                node2 = list.getNode(j);
                System.out.println(node1 + " "+ node2);
                if (node2.getNext() == node1) {
                    count++;
                    System.out.println("count " + count);
                }
                if (count > 1) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

}
