import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public http: HttpClient) { }

  public findAll() {
    return this.http.get<User[]>("api/v1/user/all");
  }

  public findById(id:number) {
    return this.http.get<User>(`api/v1/user/${id}/id`);
  }
}
