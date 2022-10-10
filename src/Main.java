import java.util.ArrayList;
public class Main {
    //быстрая сортировка
    private  static StudentComp comp = new StudentComp();
    public static void qSort(Object[] array, int top, int bot){ //принимает на вход границы массива
        if(array == null || array.length == 0) return;
        if(top <= bot) return;

        Object middle = array[(bot + top)/2]; //средняя часть
        ArrayList<Object> left = new ArrayList<>();
        ArrayList<Object> right = new ArrayList<>();
        ArrayList<Object> eq = new ArrayList<>();
        for(int i = bot; i <= top; i++){ //добавляем в правую часть элементы которые больше середины
            if(comp.compare(array[i], middle) > 0){
                right.add(array[i]);
            }
            else if (comp.compare(array[i], middle) < 0) //в левую, которые меньше
                left.add(array[i]);
            else eq.add(array[i]);
        }
        Object[] leftArr;
        Object[] rightArr;
        if(left.size()>0) { //рекурсия по левой части
            leftArr = left.toArray();
            qSort(leftArr, left.size() - 1, 0);
            System.arraycopy(leftArr, 0, array, bot, left.size());
        }
        System.arraycopy(eq.toArray(), 0, array, bot+left.size(), eq.size());

        if(right.size() > 0) { //рекурсия по правой части
            rightArr = right.toArray();
            qSort(rightArr, right.size() - 1, 0);
            System.arraycopy(rightArr, 0, array, bot+left.size() + eq.size(), right.size());

        }

    }
    //сортировка слиянием
    public static void mergeSort(Student[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2; //берём середину
        Student[] l = new Student[mid]; //создаём объект середины (идущий влево)
        Student[] r = new Student[n - mid]; //объект середины (идущий вправо)

        System.arraycopy(a, 0, l, 0, mid);
        System.arraycopy(a, mid, r, 0, n - mid);
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    public static void merge(
            Student[] a, Student[] l, Student[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].compareTo(r[j]) <= 0) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
    public static void main(String[] args) {
        Student[] studentlist = new Student[]{
                new Student(12, 4),
                new Student(10, 5),
                new Student(189, 81),
                new Student(2, 320)
        };
        for (int i=0;i < studentlist.length; i++) {
            System.out.println(studentlist[i]);
        }
        System.out.println();
        //сортировка вставками по id
        for (int i = 1; i < studentlist.length; i++) { //идём по массиву слева направо
            Student current = studentlist[i]; //создаём временный буффер
            int j = i-1; //при этом слева оставляем отсортированную часть
            for(; j >= 0 && current.compareTo(studentlist[j]) < 0; j--) { //ищем точку для вставки
                studentlist[j+1] = studentlist[j];
            }
            studentlist[j+1] = current;
        }
        for (int i=0;i < studentlist.length; i++) {
            System.out.println(studentlist[i]);
        }

        // Быстрая сортировка по оценке
        System.out.println();
        qSort(studentlist, studentlist.length-1, 0);
        for (int i=0;i < studentlist.length; i++) {
            System.out.println(studentlist[i]);
        }

        // сортировка слиянием для двух списков
        System.out.println();
        Student[] studentlist2 = new Student[]{
                new Student(35, 412),
                new Student(16, 105),
                new Student(18, 128),
                new Student(222, 201)
        };
        Student[] allstudentlist = new Student[studentlist.length + studentlist2.length];
        System.arraycopy(studentlist, 0, allstudentlist,0,studentlist.length);
        System.arraycopy(studentlist2, 0, allstudentlist,studentlist.length, studentlist2.length);
        mergeSort(allstudentlist, allstudentlist.length);
        for (int i=0;i < allstudentlist.length; i++) {
            System.out.println(allstudentlist[i]);
        }
    }
}