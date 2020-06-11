export class SignUpDto {
    username: string;
    password: string;
    name: string;
    email: string;

    constructor(username, password, name, email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}