import { Component, OnInit } from '@angular/core';
import {UserService} from "../service/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../service/user";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  public user = new User(null, "", "", "", "");

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if (param["id"] === "new") {
        this.user = new User(null, "", "", "", "");
      }
      this.userService.findById(param["id"])
        .subscribe(user => {
          this.user = user;
        }, error => {
          console.log(error);
        })
    })
  }

}
