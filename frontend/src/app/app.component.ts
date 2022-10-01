import {Component, OnInit} from '@angular/core';
import { ApiService } from './api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{

  constructor(private apiService: ApiService) {
    this.reset();
  }

  title = 'frontend';
  products: Array<Product> = [];
  alerts: Array<Alert> = [];

  getAllModels() {
		this.apiService.get("/req").subscribe((data: any) => {
			console.log(data);
			this.products = data;
		});
	}

  subscribeAndGetMessage() {
    console.log("BEGIN");

    const eventSource = this.apiService.subscribeToEvent("/api/event");

    eventSource.onmessage = (e) => {
      console.log('connection message');
      console.log(e.data);
      this.alerts.push({
        id: e.data.id,
        type: e.data.type,
        message: e.data.message
      })
    }
    eventSource.onerror = (e) => {
      console.log('connection error');
      console.log(e);
      eventSource.close();
    }
    eventSource.onopen = (e) => {
      console.log('connection open');
      console.log(e);
    }

    this.getMessage();

    // eventSource.addEventListener("message", message => console.log("======================================\n" + message.data + "\nEND"));

  }

  getMessage() {
    console.log("sendAndReceiveMessage");
    this.apiService.sendAndReceiveMessage("/api/deals/unpublished").subscribe((data: any) => {
    // this.apiService.sendAndReceiveMessage("/event/message/123").subscribe((data: any) => {
      console.log("ReceivedMessage");
      console.log(data);
      this.products = data;
    });
  }

  close(alert: Alert) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }

  reset() {
    this.alerts = [];
  }
}

export interface Product {
  name: string;
  age: number;
}

export interface Alert {
  id: string
  type: string;
  message: string;
}
