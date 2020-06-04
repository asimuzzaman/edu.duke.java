package weektwo;

public class Driver {
    public static void main(String[] args) {
//        QuakeSortWithTwoArrayLists qs = new QuakeSortWithTwoArrayLists();
//        qs.testSort();

//        QuakeSortInPlace qp = new QuakeSortInPlace();
//        qp.testSort();

        DifferentSorters ds = new DifferentSorters();
//        ds.sortWithCompareTo();
//        ds.sortByTitleAndDepth();
        ds.sortByLastWordInTitleThenByMagnitude();
    }
}
