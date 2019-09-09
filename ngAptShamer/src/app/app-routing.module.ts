import { AdminComponent } from './components/admin/admin.component';
import { AptComplaintsComponent } from './apt-complaints/apt-complaints.component';
import { CardComponent } from './components/card/card.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { ComplexDetailComponent } from './components/complex-detail/complex-detail.component';
import { LogoutComponent } from './components/logout/logout.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { TemplateComponent } from './components/template/template.component';
import { ComplexComponent } from './components/complex/complex.component';
import { ComplaintFormComponent } from './components/complaint-form/complaint-form.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'admin', component: AdminComponent},
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent},
  { path: 'template', component: TemplateComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'card', component: CardComponent},
  { path: 'complaints', component: AptComplaintsComponent},
  { path: 'complexes', component: ComplexComponent},
  { path: 'complexes/:id', component: ComplexDetailComponent},
  { path: 'complexes/:id/complaints/:cid', component: ComplaintComponent },
  { path: 'complexes/:id/complaints', component: ComplaintFormComponent},

  // NOT FOUND COMPONENT IS CATCH ALL, MUST REMAIN LAST PATH
  { path: 'notfound', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
