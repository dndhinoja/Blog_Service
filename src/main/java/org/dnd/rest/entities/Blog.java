package org.dnd.rest.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Blog")
public class Blog implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @Column(name = "blog_title")
    @NotNull
    @Size(min = 2, message = "min 2 character")
    private String blogTitle;

    @NotBlank
    @Column(name = "blog_creator")
    private String blogCreator;
    @Column(name = "yearsOfPost")
    private int yearOfPost;
}
