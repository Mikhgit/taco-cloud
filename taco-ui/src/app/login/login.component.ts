import { Component, OnInit } from '@angular/core';
import { OAuthErrorEvent, OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'login-tacocloud',
  templateUrl: 'login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  model = {
    password: '',
    username: ''
  };

  constructor(private oAuthService: OAuthService) {
    this.oAuthService.events.subscribe(e => (e instanceof OAuthErrorEvent) ? console.error(e) : console.warn(e));
  }

  public  login(): void {
    this.oAuthService.initImplicitFlow();
  }

  public logout(): void {
    this.oAuthService.logOut();
  }

  ngOnInit(): void {
  }
}
