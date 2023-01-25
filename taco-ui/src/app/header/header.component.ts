import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart/cart-service';
import { OAuthErrorEvent, OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'taco-header',
  templateUrl: 'header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  cart: CartService;

  constructor(cart: CartService, private oAuthService: OAuthService) {
    this.cart = cart;
    this.oAuthService.events.subscribe(e => (e instanceof OAuthErrorEvent) ? console.error(e) : console.warn(e));
  }

  public login(): void {
    this.oAuthService.initImplicitFlow();
  }

  public logout(): void {
    this.oAuthService.logOut();
  }

  ngOnInit() { }
}
