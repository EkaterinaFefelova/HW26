import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Task1
        Set<Set<String>> sets = new HashSet<>();
        Set<String> strings = new HashSet<>();
        strings.add("раз");
        strings.add("два");
        strings.add("три");
        Set<String> strings2 = new HashSet<>();
        strings2.add("Первое");
        strings2.add("Второе");
        strings2.add("Семьдесят шестое");
        Set<String> strings3 = new HashSet<>();
        strings3.add("12");
        strings3.add("783");
        strings3.add("37288736");
        Set<String> strings4 = new HashSet<>();
        strings4.add("Проверка");
        strings4.add("Струн");
        strings4.add("Раз-Два");

        sets.add(strings);
        sets.add(strings2);
        sets.add(strings3);
        sets.add(strings4);

        System.out.println("Общее количество букв в коллекции: " +
                sets.stream()
                        .flatMap(s->s.stream())
                        .mapToInt(s->s.length())
                        .sum());

        System.out.println("Длина самого большого слова в коллекции: " +
                sets.stream()
                        .flatMap(s->s.stream())
                        .mapToInt(s->s.length())
                        .max()
                        .getAsInt());


        List<Employee> employees = EmployeeFactory.createEmployee();

        Employee maxKpiEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getKpi))
                .get();
        System.out.println("\nРаботника с самым большим KPI зовут "
                + maxKpiEmployee.getName() + " "
                + maxKpiEmployee.getSurname()
        );

        System.out.println("\nСотрудники, которым пора на пенсию: ");
        employees.stream()
                .filter(e->e.getAge()>65)
                .forEach(System.out::println);


        System.out.println("\nСотрудник с самой высокой зарплатой:\n" +
                        employees.stream()
                                .max(Comparator.comparing(Employee::getSalary))
                                .get());

        System.out.println("\nИностранные сотрудники");
        employees.stream()
                .filter(e->e.getName()
                .matches("[A-z]+"))
                .forEach(System.out::println);

        System.out.println("\nСписок сотрудников с kpi выше среднего");
        employees.stream()
                .filter(e-> e.getKpi() >
                        (employees.stream()
                                .mapToInt(Employee::getKpi)
                                .average())
                                .getAsDouble())
                .forEach(System.out::println);

        System.out.println("\nЗначение среднего kpi у русских работников, " +
                "которым меньше 45 лет и зп которых больше 20000");
        double avgKPI = employees.stream()
                .filter(n->n.getName().matches("[А-яЁё]+"))
                .filter(e->e.getAge() < 45)
                .filter(emp->emp.getSalary()>20000)
                .mapToInt(Employee::getKpi)
                .average()
                .orElse(0);
        System.out.println(avgKPI);

       //Решение задания 4
        Map<String, Employee> namesToEmployees = new HashMap<>();

        employees.stream()
                .filter(e->e.getAge()<35 && e.getSalary()>10000)
                .forEach(e->namesToEmployees.put((e.getSurname() + " " + e.getName()), e));

        //Альтернативное решение задания 4
        Map<String,Employee> namesToEmployees1 = employees.stream()
                .filter(e->e.getAge()<35 && e.getSalary()>10000)
                .collect(Collectors.toMap(e->e.getSurname() + " " + e.getName(), e->e));


        System.out.println("\nСписок имена-сотрудники из задания 4: ");
        namesToEmployees.entrySet().forEach(e->System.out.println(e.getKey() + " " + e.getValue()));
    }
}