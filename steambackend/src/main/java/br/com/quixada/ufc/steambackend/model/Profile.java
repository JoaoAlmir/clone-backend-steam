package br.com.quixada.ufc.steambackend.model;

public class Profile {
    private String name;
    private String email;
    private String nickName;
    private String location;
    // private List<Integer> library;
    // private List<Integer> wishlist;
    // private List<Integer> friends;
    private int level;

    public Profile(){}

    public Profile(String name, String email, String nickname, String location){
        this.name = name;
        this.email = email;
        this.nickName = nickname;
        this.location = location;
        // this.library = new ArrayList<Integer>();
        // this.wishlist = new ArrayList<Integer>();
        // this.friends = new ArrayList<Integer>();
        this.level = 1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // public List<Integer> getLibrary() {
    //     return this.library;
    // }

    // public void setLibrary(List<Integer> library) {
    //     this.library = library;
    // }

    // public List<Integer> getWishlist() {
    //     return this.wishlist;
    // }

    // public void setWishlist(List<Integer> wishlist) {
    //     this.wishlist = wishlist;
    // }

    // public List<Integer> getFriends() {
    //     return this.friends;
    // }

    // public void setFriends(List<Integer> friends) {
    //     this.friends = friends;
    // }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
