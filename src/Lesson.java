import java.time.LocalTime;

/**
 * Created by Daniel Bojic
 * Date: 2020-12-14
 * Time: 12:06
 * Project: Skolsystem VG
 * Copyright: MIT
 */
public class Lesson extends Database {
    Course course;

    LocalTime lessonStart;
    LocalTime lessonEnd;

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setLessonStart(LocalTime lessonStart) {
        this.lessonStart = lessonStart;
    }

    public void setLessonEnd(LocalTime lessonEnd) {
        this.lessonEnd = lessonEnd;
    }

    Lesson(String course, String start, String end){
        setCourse(searchCourse(course));
        setLessonStart(LocalTime.parse(start));
        setLessonEnd(LocalTime.parse(end));
    }

    public LocalTime getLessonStart() {
        return lessonStart;
    }

    public LocalTime getLessonEnd() {
        return lessonEnd;
    }

    public LocalTime lengthOfLesson(){
      LocalTime lenght = getLessonEnd().minusHours(getLessonStart().getHour());
      lenght.minusMinutes(getLessonStart().getMinute());

      return lenght;
    }

}
