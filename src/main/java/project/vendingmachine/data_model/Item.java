package project.vendingmachine.data_model;

import com.fasterxml.jackson.annotation.JsonView;
import project.vendingmachine.api.ApiController;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="type"))
public class Item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ApiController.class)
    private int id;
    @NotNull
    @JsonView(ApiController.class)
    private String type;
    @NotNull
    private Integer maxCount = 10;
    @NotNull Integer count  = 0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
