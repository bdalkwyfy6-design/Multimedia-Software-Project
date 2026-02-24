

package com.portfolio;

import java.util.ArrayList;
import java.util.List;

// واجهة المشروع (Interface) - تحدد العمليات الأساسية لكل مشروع مرفوع
interface StudentProject {
    void displayProjectInfo(); // عرض معلومات المشروع
    void playOrView();         // تشغيل أو عرض العمل
}

// صنف مشاريع الفيديو (VideoProject)
class VideoProject implements StudentProject {
    private String title;
    private String studentName;
    private int duration; // بالثواني
    private String resolution;

    public VideoProject(String title, String studentName, int duration, String resolution) {
        this.title = title;
        this.studentName = studentName;
        this.duration = duration;
        this.resolution = resolution;
    }

    @Override
    public void displayProjectInfo() {
        int mins = duration / 60;
        int secs = duration % 60;
        System.out.println("[مشروع فيديو] العنوان: " + title + " | الطالب: " + studentName + 
                           " | المدة: " + mins + "د " + secs + "ث | الدقة: " + resolution);
    }

    @Override
    public void playOrView() {
        System.out.println(">> جاري تشغيل الفيديو بدقة " + resolution + " للمشروع: " + title);
    }
}

// صنف مشاريع الصور (ImageProject)
class ImageProject implements StudentProject {
    private String title;
    private String studentName;
    private String format;
    private int width;
    private int height;

    public ImageProject(String title, String studentName, String format, int width, int height) {
        this.title = title;
        this.studentName = studentName;
        this.format = format;
        this.width = width;
        this.height = height;
    }

    @Override
    public void displayProjectInfo() {
        System.out.println("[مشروع صور] العنوان: " + title + " | الطالب: " + studentName + 
                           " | التنسيق: " + format + " | الأبعاد: " + width + "x" + height);
    }

    @Override
    public void playOrView() {
        System.out.println(">> جاري عرض الصورة بالحجم الكامل (" + width + "x" + height + ") للمشروع: " + title);
    }
}

// صنف مشاريع الموشن جرافيك (MotionGraphicProject)
class MotionGraphicProject implements StudentProject {
    private String title;
    private String studentName;
    private String softwareUsed; // البرنامج المستخدم (مثل After Effects)

    public MotionGraphicProject(String title, String studentName, String softwareUsed) {
        this.title = title;
        this.studentName = studentName;
        this.softwareUsed = softwareUsed;
    }

    @Override
    public void displayProjectInfo() {
        System.out.println("[موشن جرافيك] العنوان: " + title + " | الطالب: " + studentName + 
                           " | البرنامج المستخدم: " + softwareUsed);
    }

    @Override
    public void playOrView() {
        System.out.println(">> جاري تشغيل عرض الموشن جرافيك المصمم ببرنامج " + softwareUsed + " للمشروع: " + title);
    }
}

// مصنع المشاريع (ProjectFactory) - المسؤول عن إنشاء الكائنات بناءً على النوع
class ProjectFactory {
    /**
     * طريقة المصنع لإنشاء كائنات المشاريع
     * @param type نوع المشروع (VIDEO, IMAGE, MOTION)
     * @param title عنوان المشروع
     * @param studentName اسم الطالب
     * @param param1 معامل إضافي (مثل المدة للفيديو أو التنسيق للصور)
     * @param param2 معامل إضافي (مثل الدقة للفيديو أو العرض للصور)
     * @return كائن من نوع StudentProject أو null إذا كان النوع غير معروف
     */
    public static StudentProject createProject(String type, String title, String studentName, Object param1, Object param2) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        switch (type.toUpperCase()) {
            case "VIDEO":
                return new VideoProject(title, studentName, (int)param1, (String)param2);
            case "IMAGE":
                return new ImageProject(title, studentName, (String)param1, (int)param2, (int)param2); // نفترض العرض والطول متساويين للتبسيط
            case "MOTION":
                return new MotionGraphicProject(title, studentName, (String)param1);
            default:
                throw new IllegalArgumentException("نوع المشروع غير مدعوم: " + type);
        }
    }
}

// الصنف الرئيسي لتشغيل النظام واختباره
public class StudentPortfolioSystem {
    public static void main(String[] args) {
        System.out.println("****************************************************");
        System.out.println("   منصة عرض أعمال الطلبة التفاعلية - Student Portfolio   ");
        System.out.println("****************************************************");

        // قائمة لتخزين مشاريع الطلبة المختلفة
        List<StudentProject> gallery = new ArrayList<>();

        try {
            // إضافة مشاريع متنوعة باستخدام المصنع
            gallery.add(ProjectFactory.createProject("VIDEO", "فيلم وثائقي عن غزة", "أحمد محمد", 180, "1080p"));
            gallery.add(ProjectFactory.createProject("IMAGE", "تصميم شعار الجامعة", "سارة علي", "PNG", 2000));
            gallery.add(ProjectFactory.createProject("MOTION", "إعلان توعوي عن البيئة", "محمود حسن", "After Effects", null));
            gallery.add(ProjectFactory.createProject("VIDEO", "موشن جرافيك ثلاثي الأبعاد", "ليلى خالد", 45, "4K"));

            // عرض ومعالجة جميع المشاريع في المعرض
            System.out.println("\n--- استعراض معرض أعمال الطلبة المتميزين ---");
            for (StudentProject project : gallery) {
                project.displayProjectInfo();
                project.playOrView();
                System.out.println("----------------------------------------------------");
            }

        } catch (Exception e) {
            System.err.println("حدث خطأ أثناء معالجة المشاريع: " + e.getMessage());
        }

        System.out.println("\nتمت العملية بنجاح. نتمنى لجميع الطلبة التوفيق في مسيرتهم الإبداعية!");
    }
}
