import {Component, OnInit, Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'recent-tacos',
  templateUrl: 'recents.component.html',
  styleUrls: ['./recents.component.css']
})

@Injectable()
export class RecentTacosComponent implements OnInit {
  recentTacos: any;
  allIngredients: any;

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/ingredients')
      .subscribe(data => {
        this.allIngredients = data;
      });
    this.httpClient.get('http://localhost:8080/tacos/recent') // <1>
      .subscribe(data => {
        this.recentTacos = data;
        Object.keys(this.recentTacos).forEach(key => {
          this.recentTacos[key].ingredients = Object.values(this.recentTacos[key].ingredients)
            .map(value => {
              return this.allIngredients.find(ingredient => ingredient.id === value);
            });
        });
      });
  }
}
