import { Component, OnInit } from '@angular/core';
import {UserService} from "../service/user.service";
import {User} from "../service/user";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  users: User[] = [];

  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.userService.findAll()
      .subscribe(users => {
        this.users = users;
      }, error => {
        console.log(error)
      });
  }

}
