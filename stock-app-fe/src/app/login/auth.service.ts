import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SignUpDto } from '../sign-up/signup.dto';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { tap, map } from 'rxjs/operators';
import { LoginUser } from '../dto/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUser;

  private apiUrlOrders: string = `${environment.apiUrl}`


  constructor(private http: HttpClient) { }

  login(username: any, password: any) {
    return this.http.post(`${this.apiUrlOrders}/login`, new LoginUser(username,password));
    // throw new Error("Method not implemented.");
  }


  signUp(signUpDto: SignUpDto) {
    return this.http.post(`${this.apiUrlOrders}/sign-up`, signUpDto, {responseType: 'text'});
    // .pipe(
    //   tap(()=>console.log("mue")),
    //   map(res => {
    //     res = new Object();
    //     console.log("MULTA MUIE");
    //   }));
  }

  getCurrentUser(){
    return this.currentUser;
  }

  setCurrentUser(currentUser){
    this.currentUser = currentUser;
  }
  
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    localStorage.removeItem('principal');
    this.currentUser = null;
    // this.currentUserSubject.next(null);
  }



}
