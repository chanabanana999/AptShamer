import { UserProfile } from './user-profile';

export class User {
  id: number;
  username: string;
  password: string;
  role: string;
  enabled: boolean;
  profile: UserProfile;

  constructor(id?: number, username?: string, password?: string, role?: string,
              enabled?: boolean, profile?: UserProfile) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.enabled = enabled;
    this.profile = profile;
  };
}
