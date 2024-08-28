import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { MybookingComponent } from './mybooking/mybooking.component';
import { PackageListingComponent } from './package-listing/package-listing.component';
import { FaqComponent } from './faq/faq.component';
import { WishlistComponent } from './wishlist/wishlist.component';

export const routes: Routes = [
    {path:"home",component:HomeComponent},
    {path:"sign-up",component:SignUpComponent},
    {path:"sign-in",component:SignInComponent},
    {path:"bookings",component:MybookingComponent},
    {path:"packages",component:PackageListingComponent},
    {path:"faq",component:FaqComponent},
    {path:'wishlist',component: WishlistComponent}
];
