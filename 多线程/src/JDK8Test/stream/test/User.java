package JDK8Test.stream.test;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author Gaoming
 * @Email mineok@foxmail.com
 * @Date 2021/01/20/ 21:48
 * @Description
 */
public class User {

    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private void userParametersAreStandard(Integer id, String name) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        if (id == 0) {
            throw new IllegalArgumentException();
        }
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        if (id > Integer.MAX_VALUE / 1000) {
            throw new IllegalArgumentException();
        }
        if (name == null) {
            throw new IllegalArgumentException();
        }
        if (name.equals("")) {
            throw new IllegalArgumentException();
        }
        if (name.equals("''")) {
            throw new IllegalArgumentException();
        }
        if (!name.matches("[\\\\u4e00-\\\\u9fa5]*")) {
            throw new IllegalArgumentException();
        }
    }

    private void userParametersAreStandard2(Integer id, String name) {
        if (id == null || name == null) {
            throw new IllegalArgumentException();
        }
        if (id <= 0 || id > Integer.MAX_VALUE / 1000) {
            throw new IllegalArgumentException();
        }
        if (name.equals("") || name.equals("''") || !name.matches("[\\\\u4e00-\\\\u9fa5]*")) {
            throw new IllegalArgumentException();
        }
    }

    private void userParametersAreStandard3(Integer id, String name) {
        id = Optional.ofNullable(id).
                filter(userId -> userId > 0 && userId < Integer.MAX_VALUE / 1000).
                orElseThrow(IllegalArgumentException::new);
        name = Optional.ofNullable(name).
                filter(username -> !username.equals("") && username.matches("[\\\\u4e00-\\\\u9fa5]*")).
                orElseThrow(IllegalArgumentException::new);
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
