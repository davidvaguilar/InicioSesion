package cl.santotomas.iniciosesion.modelo;

public class Usuario {
    private Integer id;
    private String nombre;
    private String email;
    private String password;

    public String getPassword() {
        return password;   // transformar MD5 hash
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean esVacio(){
        if( this.nombre.isEmpty() && this.email.isEmpty() && this.password.isEmpty() )
            return true;
        return false;
    }

    public boolean claveIgual(String repetir){
        if(this.password.equals(repetir))
            return true;
        return false;
    }


    public boolean emailIgual(String otroemail){
        if( this.email.equals(otroemail))
            return true;
        return false;
    }

    public boolean login(String email, String password){
        if( this.email.equals(email) && this.password.equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
