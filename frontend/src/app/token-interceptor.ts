import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authTokenKey = 'Authorization';
    const authTokenValue = 'Bearer eyJraWQiOiJZcnBBZjN5OXNGVzJkMW9RclpxYzh6ZW01QnY3VzJlUDl6Kzh1bTVBQ3R3PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIxZDk0YjY5My0xNmE5LTQ0MzMtOWMxZS1iZjE1NTY1MzczMzIiLCJjb2duaXRvOmdyb3VwcyI6WyJ1cy1lYXN0LTFfNENrZFEzYnlpX0dvb2dsZU9JREMiXSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfNENrZFEzYnlpIiwidmVyc2lvbiI6MiwiY2xpZW50X2lkIjoiNmk0YjRjbThjY2s5ZXE4bXFsZDdrN3NhdDYiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6Im9wZW5pZCBodHRwczpcL1wvOHE2M2k1bjF2MS5leGVjdXRlLWFwaS51cy1lYXN0LTEuYW1hem9uYXdzLmNvbVwvYWxsIGVtYWlsIiwiYXV0aF90aW1lIjoxNjY0NDgwMjI2LCJleHAiOjE2NjQ1MTI2MjYsImlhdCI6MTY2NDQ4MDIyNiwianRpIjoiOTZkMTA2NjctMjA3OC00NWZiLWI5NjEtNDJhY2M0MDc4MTUwIiwidXNlcm5hbWUiOiJHb29nbGVPSURDXzEwMTY5NDUzNTIzMDYwNDg2MDY2OSJ9.pBPMH9sW67GZxNraT2mYqC6wX53K1N0juq2fk5_nHAFEaW1fok1WvJkDk1b46kmSEuZolpxQeFaVjvKoY-mw01HTXKrWu3RcgoAwNPBgOS82dv25igBkk8SPMTAPVXc5XyPmKw94a1b0TUriWUthFJz-L45J4uKRJjqXmFA_8biwIxiXdAL9KOjqkuzmg694lahMDRg_GLQHGz2wVPy4OiuhSDlesy5r8aW61lz2fGa9IjzG5-N2S_6HbibnUvgRd_W-1b1cXf_Nf8JqvKJIM9mlH8V-ML3CzqWnAtGix4flpVkbW9cQqhZgn21fgO5EfINs5pwfWewwnHnSwWJBiw';

    const authEntitiesKey = 'authorized_entities';
    const authEntitiesValue = 'eyJzZWNfdXNlcl9pZCI6IDI0LCAicHVibGlzaGVyX2dyb3VwX2lkIjogIjE3MTMiLCAiaXNfYWRtaW4iOiB0cnVlLCAicHJvZmlsZXMiOiBbXX0=';

    const authUserKey = 'authorized_user';
    const authUserValue = 'ibabayev@yieldmo.com';

    console.log("*********** INTERCEPTOR ************")

    const modifiedReq = req.clone({
      headers: req.headers
                    .set(authTokenKey, authTokenValue)
                    .set(authEntitiesKey, authEntitiesValue)
                    .set(authUserKey, authUserValue)
    });
    return next.handle(modifiedReq);
  }
}
