import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'security-user',
        data: { pageTitle: 'vksApplicationApp.securityUser.home.title' },
        loadChildren: () => import('./security-user/security-user.module').then(m => m.SecurityUserModule),
      },
      {
        path: 'security-role',
        data: { pageTitle: 'vksApplicationApp.securityRole.home.title' },
        loadChildren: () => import('./security-role/security-role.module').then(m => m.SecurityRoleModule),
      },
      {
        path: 'security-permission',
        data: { pageTitle: 'vksApplicationApp.securityPermission.home.title' },
        loadChildren: () => import('./security-permission/security-permission.module').then(m => m.SecurityPermissionModule),
      },
      {
        path: 'parameter-lookup',
        data: { pageTitle: 'vksApplicationApp.parameterLookup.home.title' },
        loadChildren: () => import('./parameter-lookup/parameter-lookup.module').then(m => m.ParameterLookupModule),
      },
      {
        path: 'member',
        data: { pageTitle: 'vksApplicationApp.member.home.title' },
        loadChildren: () => import('./member/member.module').then(m => m.MemberModule),
      },
      {
        path: 'member-bank',
        data: { pageTitle: 'vksApplicationApp.memberBank.home.title' },
        loadChildren: () => import('./member-bank/member-bank.module').then(m => m.MemberBankModule),
      },
      {
        path: 'member-assets',
        data: { pageTitle: 'vksApplicationApp.memberAssets.home.title' },
        loadChildren: () => import('./member-assets/member-assets.module').then(m => m.MemberAssetsModule),
      },
      {
        path: 'member-land-assets',
        data: { pageTitle: 'vksApplicationApp.memberLandAssets.home.title' },
        loadChildren: () => import('./member-land-assets/member-land-assets.module').then(m => m.MemberLandAssetsModule),
      },
      {
        path: 'nominee',
        data: { pageTitle: 'vksApplicationApp.nominee.home.title' },
        loadChildren: () => import('./nominee/nominee.module').then(m => m.NomineeModule),
      },
      {
        path: 'society',
        data: { pageTitle: 'vksApplicationApp.society.home.title' },
        loadChildren: () => import('./society/society.module').then(m => m.SocietyModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
