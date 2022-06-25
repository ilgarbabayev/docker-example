import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private SERVER_URL = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  public get(url:string){  
		return this.httpClient.get(this.SERVER_URL + url);
	}
}
