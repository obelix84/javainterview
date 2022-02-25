export class User {

  constructor(public id:number | null,
              public firstname:string,
              public lastname:string,
              public login:string,
              public password:string) {
  }
}
