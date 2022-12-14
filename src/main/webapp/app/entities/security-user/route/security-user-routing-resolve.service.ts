import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISecurityUser } from '../security-user.model';
import { SecurityUserService } from '../service/security-user.service';

@Injectable({ providedIn: 'root' })
export class SecurityUserRoutingResolveService implements Resolve<ISecurityUser | null> {
  constructor(protected service: SecurityUserService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISecurityUser | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((securityUser: HttpResponse<ISecurityUser>) => {
          if (securityUser.body) {
            return of(securityUser.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
