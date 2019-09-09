import { User } from './user';
import { Rating } from './rating';
import { Complaint } from './complaint';
import { Complex } from './complex';


export class UserProfile {
  comments: Comment[];
  // complaints: Complaint[];
  complex: Complex;
  displayName: string;
  email: string;
  firstName: string;
  id: number;
  imageUrl: string;
  lastName: string;
  ratings: Rating[];
  user: User;


  constructor(comments?: Comment[],complex?: Complex,
              displayName?: string, email?: string, firstName?: string, id?: number,
              imageUrl?: string, lastName?: string, ratings?: Rating[], user?: User, ) {
    this.id = id;
    this.user = user;
    // this.complaints = complaints;
    this.complex = complex;
    this.displayName = displayName;
    this.email = email;
    this.firstName = firstName;
    this.id = id;
    this.imageUrl = imageUrl;
    this.lastName = lastName;
    this.ratings = ratings;
    this.user = user;
  }

}
