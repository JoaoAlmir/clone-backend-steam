package br.com.quixada.ufc.steambackend.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profile {
    @Getter @Setter
    private Integer idUser;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String nickName;

    @Getter @Setter
    private String location;

    @Getter @Setter
    private List<Integer> library;

    @Getter @Setter
    private List<Integer> wishlist;

    @Getter @Setter
    private List<Integer> friends;

    @Getter @Setter
    private int level;
    
}
