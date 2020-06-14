import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  private userApiUrl = `${environment.apiUrl}/api/users`

  constructor(private http: HttpClient) { }

  getUserInfo(username){
    return this.http.get(`${this.userApiUrl}/${username}`);
  }
}
