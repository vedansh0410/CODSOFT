import java.util.*;

public class Studentgradecalculator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("--------------STUDENT GRADE CALCULATOR-------------");
        System.out.println();
        System.out.print("Number of Subjects : ");
        int no_of_subjects=sc.nextInt();
        int total=0;
        for(int i=1;i<=no_of_subjects;i++){
        if(i==1)    
        System.out.print("Enter marks scored in "+i+"st subject : ");
        else if(i==2)
        System.out.print("Enter marks scored in "+i+"nd subject : ");
        else if(i==3)
        System.out.print("Enter marks scored in "+i+"rd subject : ");
        else
        System.out.print("Enter marks scored in "+i+"th subject : ");
        int marks=sc.nextInt();
        total+=marks;
    }
         System.out.println("--------------------------------------");
         float average=((float)total)/(float)no_of_subjects;
         System.out.println("  Total marks : " +total);
         System.out.println("  Average percentage score : "+average+"%");
         System.out.print("  Grade score : ");
         if(average>=90)
         System.out.println("A");
         else if(average>=80)
         System.out.println("B");
         else if(average>=70)
         System.out.println("C");
         else if(average>=60)
         System.out.println("D");
         else
         System.out.println("F");
         System.out.println("--------------------------------------");
         sc.close();
    }
}
