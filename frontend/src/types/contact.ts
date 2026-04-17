export type RequestType =
  | 'Demande de visite'
  | 'Être rappelé.e'
  | 'Plus de photos';

export type Civility = 'Mme' | 'M.';

export type AvailabilityDay =
  | 'Lundi'
  | 'Mardi'
  | 'Mercredi'
  | 'Jeudi'
  | 'Vendredi'
  | 'Samedi';

export type AvailabilityHour =
  | '7'
  | '8'
  | '9'
  | '10'
  | '11'
  | '12'
  | '14'
  | '15'
  | '16'
  | '17'
  | '18';

export type AvailabilityMinute = '00' | '15' | '30' | '45';

export type Availability = {
  day: AvailabilityDay;
  hour: AvailabilityHour;
  minute: AvailabilityMinute;
};

export type FormErrors = {
  firstName?: string;
  lastName?: string;
  email?: string;
  phone?: string;
  message?: string;
  availabilities?: string;
};