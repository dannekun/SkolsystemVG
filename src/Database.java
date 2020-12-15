import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Database {

    private Person students = new Person();
    private Person teachers = new Person();
    private List<Course> courseList = new ArrayList<>();

   private List<Lesson> monday = new ArrayList();
   private List<Lesson> tuesday = new ArrayList();
   private List<Lesson> wednesday = new ArrayList();
  private   List<Lesson> thursday = new ArrayList();
    private List<Lesson> friday = new ArrayList();



    public List<Lesson> getMonday() {
        return monday;
    }

    public List<Lesson> getTuesday() {
        return tuesday;
    }

    public List<Lesson> getWednesday() {
        return wednesday;
    }

    public List<Lesson> getThursday() {
        return thursday;
    }

    public List<Lesson> getFriday() {
        return friday;
    }

    public Person getStudents() {
        return students;
    }

    public Person getTeachers() {
        return teachers;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void addStudent(Person student) {
        this.students.addPerson(student);
    }

    public void addTeacher(Person teacher) {
        this.teachers.addPerson(teacher);
    }

    public Course searchCourse(String string) {
        for (Course course : getCourseList()) {
            if (string.equalsIgnoreCase(course.getName())) {
                return course;
            }
        }
        return null;
    }

    public Person searchTeacher(String string) {
        for (Person person : getTeachers().getPersons()) {
            if (string.equalsIgnoreCase(person.getName())) {
                return person;
            }
        }
        return null;
    }

    public Person searchStudent(String string) {
        for (Person person : getStudents().getPersons()) {
            if (string.equalsIgnoreCase(person.getName())) {
                return person;
            }
        }
        return null;
    }

    public String printCourse(String courseToSearchAndPrint) {
        Course courseToPrint = searchCourse(courseToSearchAndPrint);

        String textToPrint = "Kurs: " + courseToPrint.getName() + "\n" + "Lärare: " + courseToPrint.getTeacher().getName() + "\n" + "Studenter: ";

        for (Person p : courseToPrint.getCourseStudentList()) {
            textToPrint += "\n" + p.getName();
        }

        return textToPrint;
    }

    public String printTeacher(String teacherToSearchAndPrint) {
        Person teacher = searchTeacher(teacherToSearchAndPrint);

        String textToPrint = "Namn: " + teacher.getName() + "\n" + "Ålder: " + teacher.getAge() + "\n" + "Mail: " + teacher.getMail() + "\n" + "Telefonnummer: " + teacher.getNumber() + "\n" +"\n" + teacher.getName() + " utbildar de här kurserna: ";


        for (Course findCourse : teacher.getCourses()){
            textToPrint += "\n" + findCourse.getName();
        }


        textToPrint += "\n" + "\n" + teacher.getName() + " är lärare över de här eleverna: ";

        for (Course courseStudents : teacher.getCourses()){
            for (Person studentInClass : courseStudents.getCourseStudentList()){
                if (!textToPrint.contains(studentInClass.getName())){
                   textToPrint += "\n" + studentInClass.getName();
                }
            }
        }

        return textToPrint;
    }

    public String printStudent(String studentToSearchAndPrint) {
        Person studentName = searchStudent(studentToSearchAndPrint);

        String textToPrint = "Namn: " + studentName.getName() + "\n" + "Ålder: " + studentName.getAge() + "\n" + "Mail: " + studentName.getMail() + "\n" + "Telefonnummer: " + studentName.getNumber() +"\n" +"\n" + studentName.getName() + " går på dom här kurserna: ";

        for (Course coursesStudentIsIn : studentName.getCourses()){
            textToPrint += "\n" + coursesStudentIsIn.getName();
        }

        textToPrint += "\n" + "\n" + studentName.getName() + " blir utbildad av de här lärarna: ";

        for (Course coursesStudentHas : studentName.getCourses()){
                textToPrint += "\n" + coursesStudentHas.getTeacher().getName();
        }



        return textToPrint;
    }

    public String compareSchedule(String input){
        if (!input.contains(" ")){
            return "Skriv in två elever som går här! \nT.ex Daniel Simon";
        }
        int findspace = input.indexOf(" ");
        String firstName = input.substring(0, findspace);
        String secondName = input.substring(findspace+1);

        Person firstPerson = searchStudent(firstName);
        Person secondPerson = searchStudent(secondName);

        if (firstPerson == null || secondPerson == null){
            return "Skriv in två elever som går här! \nT.ex Daniel Simon";
        }else {
            return findScheduleToCompare(firstPerson, secondPerson);
        }





    }

    public String findScheduleToCompare(Person firstName, Person secondName){
        String textToPrint = "";

        textToPrint += "Schema för " + firstName.getName() + " & " + secondName.getName() + ": " ;
        textToPrint += "\n" + "Måndag: ";
        textToPrint += findLessonToCompare(getMonday(), firstName, secondName);
        textToPrint += "\n" + "Tisdag: ";
        textToPrint += findLessonToCompare(getTuesday(), firstName,secondName);
        textToPrint += "\n" +  "Onsdag: ";
        textToPrint += findLessonToCompare(getWednesday(), firstName,secondName);
        textToPrint += "\n" + "Torsdag: ";
        textToPrint += findLessonToCompare(getThursday(), firstName,secondName);
        textToPrint += "\n" + "Fredag: ";
        textToPrint += findLessonToCompare(getFriday(), firstName,secondName);

        return textToPrint;
    }

    public String findLessonToCompare(List<Lesson> lessonOfTheDay, Person firstname, Person secondName){
        String textToPrint = "";

            for (Lesson lessons : lessonOfTheDay){
                if (lessons.getCourse().getCourseStudentList().contains(firstname) && lessons.getCourse().getCourseStudentList().contains(secondName)){
                    textToPrint += "\n" + lessons.getCourse().getName() + " " + lessons.getLessonStart() + " - " + lessons.getLessonEnd();
                }
            }


        if (textToPrint.equals("")){
            return  "\nInga lektioner tillsammans!";
        }else {
            return textToPrint;
        }

    }

    public String findSchedule(String input){
        String textToPrint = "";

        String capitalLetter = input.substring(0,1);
        input = capitalLetter.toUpperCase() + input.substring(1).toLowerCase();

        textToPrint +=  "Schema för " + input + ": " ;
        textToPrint += "\n" + "Måndag: ";
        textToPrint += findLesson(getMonday(), input);
        textToPrint += "\n" + "Tisdag: ";
        textToPrint += findLesson(getTuesday(), input);
        textToPrint += "\n" +  "Onsdag: ";
        textToPrint += findLesson(getWednesday(), input);
        textToPrint += "\n" + "Torsdag: ";
        textToPrint += findLesson(getThursday(), input);
        textToPrint += "\n" + "Fredag: ";
        textToPrint += findLesson(getFriday(), input);

        return textToPrint;
    }

    public String findLesson(List<Lesson> lessonOfTheDay, String input){
        String textToPrint = "";

        if (searchStudent(input) != null){
            for (Lesson lessons : lessonOfTheDay){
                for (Person p : lessons.getCourse().getCourseStudentList()){
                    if (p.getName().equalsIgnoreCase(input)){
                        textToPrint += "\n" + lessons.getCourse().getName() + " " + lessons.getLessonStart() + " - " + lessons.getLessonEnd();
                    }
                }
            }
        }else if (searchTeacher(input) != null){
            for (Lesson lessons : lessonOfTheDay){
                if (lessons.getCourse().getTeacher().getName().equalsIgnoreCase(input)){
                    textToPrint += "\n" + lessons.getCourse().getName() + " " + lessons.getLessonStart() + " - " + lessons.getLessonEnd();
                }
            }

        }else if (searchCourse(input) != null){
            for (Lesson lessons : lessonOfTheDay){
                if (lessons.getCourse().getName().equalsIgnoreCase(input)){
                    textToPrint += "\n" + lessons.getCourse().getName() + " " + lessons.getLessonStart() + " - " + lessons.getLessonEnd();
                }
            }
        }


        if (textToPrint.equals("")){
            return  "\nLedig!";
        }else {
            return textToPrint;
        }


    }

    public void addMondayLesson(Lesson lesson){
        this.monday.add(lesson);
    }
    public void addTuesdayLesson(Lesson lesson){
        this.tuesday.add(lesson);
    }
    public void addWednesdayLesson(Lesson lesson){
        this.wednesday.add(lesson);
    }
    public void addThursdayLesson(Lesson lesson){
        this.thursday.add(lesson);
    }
    public void addFridayLesson(Lesson lesson){
        this.friday.add(lesson);
    }


    public Database() {

        Person teacherOne = new Person("Steffe", "40", "Steffe@mail.com", "0737856513");
        Person teacherTwo = new Person("Cribb", "65", "Cribb@cribb.net", "8844556644");
        Person teacherThree = new Person("Anders", "51", "Anders@mail.com", "0735654655");
        Person teacherFour = new Person("Lars", "30", "Lars@mail.com", "073565465");

        Course courseSvenska = new Course("Svenska", teacherThree);
        Course courseJava = new Course("Java", teacherFour);
        Course courseMatematik = new Course("Matematik", teacherTwo);
        Course courseIdrott = new Course("Idrott", teacherOne);

        Person studentOne = new Person("Lily", "14", "lily@mail.com", "0723221354");
        Person studentTwo = new Person("Simon", "12", "simon_kool@mail.com", "0756516516");
        Person studentThree = new Person("Daniel", "13", "daniel_small@mail.com", "98446512");
        Person studentFour = new Person("Kalle", "15", "kalle@mail.com", "654165131");
        Person studentFive = new Person("Elias", "14", "elias@mail.se", "6541321321");

        teacherOne.addCourses(courseIdrott);
        teacherTwo.addCourses(courseMatematik);
        teacherThree.addCourses(courseSvenska);
        teacherFour.addCourses(courseJava);

        studentOne.addCourses(courseSvenska);
        studentOne.addCourses(courseIdrott);
        studentOne.addCourses(courseMatematik);

        studentTwo.addCourses(courseSvenska);

        studentThree.addCourses(courseSvenska);
        studentThree.addCourses(courseJava);

        studentFour.addCourses(courseIdrott);

        studentFive.addCourses(courseJava);
        studentFive.addCourses(courseMatematik);
        studentFive.addCourses(courseIdrott);

        addCourse(courseSvenska);
        addCourse(courseJava);
        addCourse(courseMatematik);
        addCourse(courseIdrott);

        addStudent(studentOne);
        addStudent(studentTwo);
        addStudent(studentThree);
        addStudent(studentFour);
        addStudent(studentFive);

        addTeacher(teacherOne);
        addTeacher(teacherThree);
        addTeacher(teacherTwo);
        addTeacher(teacherFour);

        courseSvenska.addStudent(studentOne);
        courseSvenska.addStudent(studentTwo);
        courseSvenska.addStudent(studentThree);

        courseJava.addStudent(studentThree);
        courseJava.addStudent(studentFive);

        courseMatematik.addStudent(studentOne);
        courseMatematik.addStudent(studentFive);

        courseIdrott.addStudent(studentFour);
        courseIdrott.addStudent(studentFive);
        courseIdrott.addStudent(studentOne);


        addMondayLesson(new Lesson(courseJava, "09:00", "12:00"));
        addMondayLesson(new Lesson(courseSvenska ,"13:00","15:00"));

        addTuesdayLesson(new Lesson(courseMatematik, "10:00", "12:00"));
        addTuesdayLesson(new Lesson(courseIdrott, "13:00", "15:00"));

        addWednesdayLesson(new Lesson(courseJava, "08:00", "12:00"));
        addWednesdayLesson(new Lesson(courseMatematik, "13:00", "15:00"));

        addThursdayLesson(new Lesson(courseSvenska, "10:00", "12:00"));
        addThursdayLesson(new Lesson(courseIdrott, "13:00", "15:00"));

        addFridayLesson(new Lesson(courseJava, "09:00", "12:00"));
        addFridayLesson(new Lesson(courseMatematik, "13:00", "15:00"));




    }
}
