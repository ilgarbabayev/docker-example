import { Component } from '@angular/core';
import { ApiService } from './api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private apiService: ApiService) { }
  title = 'frontend';
  products: Array<Product> = [];

  getAllModels() {
		this.apiService.get("/req").subscribe((data: any) => {
			console.log(data);  
			this.products = data;
		});
	}
}

export interface Product {
  name: string;
  age: number;
}
