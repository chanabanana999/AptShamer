import { Rating } from './rating';
import { UserProfile } from './user-profile';
import { Contact } from './contact';
import { Complaint } from './complaint';

export class Complex {
  id: number;
  name: string;
  street: string;
  city: string;
  state: string;
  zip: string;
  imageUrl: string;
  numUnits: number;
  contacts: Contact[];
  complaints: Complaint[];
  profiles: UserProfile[];
  ratings: Rating[];

  constructor(id?: number, name?: string, street?: string, city?: string,
              state?: string, zip?: string, imageUrl?: string, numUnits?: number,
              contacts?: Contact[], complaints?: Complaint[], profiles?: UserProfile[],
              ratings?: Rating[]) {
                this.id = id;
                this.name = name;
                this.street = street;
                this.city = city;
                this.state = state;
                this.zip = zip;
                this.imageUrl = imageUrl;
                this.numUnits = numUnits;
                this.contacts = contacts;
                this.complaints = complaints;
                this.profiles = profiles;
                this.ratings = ratings;
              }
}
