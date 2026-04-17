export const REQUEST_TYPES = [
  'Demande de visite',
  'Être rappelé.e',
  'Plus de photos',
] as const;

export const CIVILITIES = ['Mme', 'M.'] as const;

export const AVAILABILITY_DAYS = [
  'Lundi',
  'Mardi',
  'Mercredi',
  'Jeudi',
  'Vendredi',
  'Samedi',
] as const;

export const AVAILABILITY_HOURS = [
  '7',
  '8',
  '9',
  '10',
  '11',
  '12',
  '14',
  '15',
  '16',
  '17',
  '18',
] as const;

export const AVAILABILITY_MINUTES = ['00', '15', '30', '45'] as const;

export type RequestType = (typeof REQUEST_TYPES)[number];
export type Civility = (typeof CIVILITIES)[number];
export type AvailabilityDay = (typeof AVAILABILITY_DAYS)[number];
export type AvailabilityHour = (typeof AVAILABILITY_HOURS)[number];
export type AvailabilityMinute = (typeof AVAILABILITY_MINUTES)[number];

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

export type ContactFormData = {
  civility: Civility;
  lastName: string;
  firstName: string;
  email: string;
  phone: string;
  requestType: RequestType;
  message: string;
};

export type SubmitStatus = {
  type: 'success' | 'error';
  message: string;
};