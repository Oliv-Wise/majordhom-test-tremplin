import {
  AVAILABILITY_DAYS,
  AVAILABILITY_HOURS,
  AVAILABILITY_MINUTES,
  CIVILITIES,
  REQUEST_TYPES,
} from '../types/contact';
import type { Availability, ContactFormData } from '../types/contact';

export const initialFormData: ContactFormData = {
  civility: CIVILITIES[0],
  lastName: '',
  firstName: '',
  email: '',
  phone: '',
  requestType: REQUEST_TYPES[0],
  message: '',
};

export const initialPendingAvailability: Availability = {
  day: AVAILABILITY_DAYS[0],
  hour: AVAILABILITY_HOURS[0],
  minute: AVAILABILITY_MINUTES[0],
};