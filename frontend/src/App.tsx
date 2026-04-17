import { useState } from 'react';
import { API_BASE_URL } from './config';
import {
  AVAILABILITY_DAYS,
  AVAILABILITY_HOURS,
  AVAILABILITY_MINUTES,
  CIVILITIES,
  REQUEST_TYPES,
} from './types/contact';
import type {
  Availability,
  ContactFormData,
  FormErrors,
  SubmitStatus,
} from './types/contact';
import {
  initialFormData,
  initialPendingAvailability,
} from './constants/contactForm';

function App() {
  const [formData, setFormData] = useState<ContactFormData>(initialFormData);
  const [pendingAvailability, setPendingAvailability] = useState<Availability>(
    initialPendingAvailability
  );
  const [availabilities, setAvailabilities] = useState<Availability[]>([]);
  const [errors, setErrors] = useState<FormErrors>({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitStatus, setSubmitStatus] = useState<SubmitStatus | null>(null);

  const handleInputChange =
    (field: 'lastName' | 'firstName' | 'email' | 'phone' | 'message') =>
    (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
      const { value } = event.target;

      setFormData((previous) => ({
        ...previous,
        [field]: value,
      }));

      if (errors[field]) {
        setErrors((previous) => ({
          ...previous,
          [field]: undefined,
        }));
      }
    };

  const handleCivilityChange = (value: ContactFormData['civility']) => {
    setFormData((previous) => ({
      ...previous,
      civility: value,
    }));
  };

  const handleRequestTypeChange = (value: ContactFormData['requestType']) => {
    setFormData((previous) => ({
      ...previous,
      requestType: value,
    }));
  };

  const handleDayChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setPendingAvailability((previous) => ({
      ...previous,
      day: event.target.value as Availability['day'],
    }));
  };

  const handleHourChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setPendingAvailability((previous) => ({
      ...previous,
      hour: event.target.value as Availability['hour'],
    }));
  };

  const handleMinuteChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setPendingAvailability((previous) => ({
      ...previous,
      minute: event.target.value as Availability['minute'],
    }));
  };

  const handleAddAvailability = () => {
    const alreadyExists = availabilities.some(
      (availability) =>
        availability.day === pendingAvailability.day &&
        availability.hour === pendingAvailability.hour &&
        availability.minute === pendingAvailability.minute
    );

    if (alreadyExists) {
      return;
    }

    setAvailabilities((previous) => [...previous, pendingAvailability]);

    if (errors.availabilities) {
      setErrors((previous) => ({
        ...previous,
        availabilities: undefined,
      }));
    }
  };

  const handleRemoveAvailability = (indexToRemove: number) => {
    setAvailabilities((previous) =>
      previous.filter((_, index) => index !== indexToRemove)
    );
  };

  const validateForm = (): FormErrors => {
    const newErrors: FormErrors = {};

    if (!formData.lastName.trim()) {
      newErrors.lastName = 'Le nom est requis.';
    }

    if (!formData.firstName.trim()) {
      newErrors.firstName = 'Le prénom est requis.';
    }

    if (!formData.email.trim()) {
      newErrors.email = "L'adresse mail est requise.";
    }

    if (!formData.phone.trim()) {
      newErrors.phone = 'Le téléphone est requis.';
    }

    if (availabilities.length === 0) {
      newErrors.availabilities = 'Ajoutez au moins une disponibilité.';
    }

    return newErrors;
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const validationErrors = validateForm();
    setErrors(validationErrors);
    setSubmitStatus(null);

    if (Object.keys(validationErrors).length > 0) {
      return;
    }

    const payload = {
      ...formData,
      availabilities,
    };

    try {
      setIsSubmitting(true);

      const response = await fetch(`${API_BASE_URL}/api/contact-requests`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      let responseBody: unknown = null;

      try {
        responseBody = await response.json();
      } catch {
        responseBody = null;
      }

      if (!response.ok) {
        const errorMessage =
          typeof responseBody === 'object' &&
          responseBody !== null &&
          'error' in responseBody &&
          typeof responseBody.error === 'string'
            ? responseBody.error
            : "Une erreur est survenue lors de l'envoi.";

        throw new Error(errorMessage);
      }

      setSubmitStatus({
        type: 'success',
        message: 'Votre demande a bien été envoyée.',
      });

      setFormData(initialFormData);
      setPendingAvailability(initialPendingAvailability);
      setAvailabilities([]);
      setErrors({});
    } catch (error) {
      setSubmitStatus({
        type: 'error',
        message:
          error instanceof Error
            ? error.message
            : "Impossible d'envoyer la demande.",
      });
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <main className="page">
      <section className="contact-card">
        <div className="contact-card__overlay" />

        <div className="contact-card__content">
          <h1 className="contact-card__title">CONTACTEZ L&apos;AGENCE</h1>

          <form className="contact-card__grid" onSubmit={handleSubmit}>
            <div className="contact-card__column">
              <h2 className="contact-card__section-title">VOS COORDONNÉES</h2>

              <div className="radio-row">
                {CIVILITIES.map((civility) => (
                  <label className="radio-option" key={civility}>
                    <input
                      type="radio"
                      name="civility"
                      checked={formData.civility === civility}
                      onChange={() => handleCivilityChange(civility)}
                    />
                    <span className="radio-dot" />
                    <span>{civility}</span>
                  </label>
                ))}
              </div>

              <div className="input-row">
                <div>
                  <input
                    type="text"
                    placeholder="Nom"
                    value={formData.lastName}
                    onChange={handleInputChange('lastName')}
                  />
                  {errors.lastName && (
                    <p className="field-error">{errors.lastName}</p>
                  )}
                </div>

                <div>
                  <input
                    type="text"
                    placeholder="Prénom"
                    value={formData.firstName}
                    onChange={handleInputChange('firstName')}
                  />
                  {errors.firstName && (
                    <p className="field-error">{errors.firstName}</p>
                  )}
                </div>
              </div>

              <input
                type="email"
                placeholder="Adresse mail"
                value={formData.email}
                onChange={handleInputChange('email')}
              />
              {errors.email && <p className="field-error">{errors.email}</p>}

              <input
                type="tel"
                placeholder="Téléphone"
                value={formData.phone}
                onChange={handleInputChange('phone')}
              />
              {errors.phone && <p className="field-error">{errors.phone}</p>}

              <div className="availability-block">
                <h2 className="contact-card__section-title">
                  DISPONIBILITÉS POUR UNE VISITE
                </h2>

                <div className="availability-row">
                  <select value={pendingAvailability.day} onChange={handleDayChange}>
                    {AVAILABILITY_DAYS.map((day) => (
                      <option key={day} value={day}>
                        {day}
                      </option>
                    ))}
                  </select>

                  <select value={pendingAvailability.hour} onChange={handleHourChange}>
                    {AVAILABILITY_HOURS.map((hour) => (
                      <option key={hour} value={hour}>
                        {hour} h
                      </option>
                    ))}
                  </select>

                  <select
                    value={pendingAvailability.minute}
                    onChange={handleMinuteChange}
                  >
                    {AVAILABILITY_MINUTES.map((minute) => (
                      <option key={minute} value={minute}>
                        {minute} m
                      </option>
                    ))}
                  </select>

                  <button
                    type="button"
                    className="add-button"
                    onClick={handleAddAvailability}
                  >
                    AJOUTER
                    <br />
                    DISPO
                  </button>
                </div>

                <div className="availability-tags">
                  {availabilities.map((availability, index) => (
                    <span
                      className="availability-tag"
                      key={`${availability.day}-${availability.hour}-${availability.minute}-${index}`}
                    >
                      {availability.day} à {availability.hour}h{availability.minute}
                      <button
                        type="button"
                        onClick={() => handleRemoveAvailability(index)}
                      >
                        ×
                      </button>
                    </span>
                  ))}
                </div>

                {errors.availabilities && (
                  <p className="field-error">{errors.availabilities}</p>
                )}
              </div>
            </div>

            <div className="contact-card__column">
              <h2 className="contact-card__section-title">VOTRE MESSAGE</h2>

              <div className="radio-row radio-row--message">
                {REQUEST_TYPES.map((requestType) => (
                  <label className="radio-option" key={requestType}>
                    <input
                      type="radio"
                      name="requestType"
                      checked={formData.requestType === requestType}
                      onChange={() => handleRequestTypeChange(requestType)}
                    />
                    <span className="radio-dot" />
                    <span>{requestType}</span>
                  </label>
                ))}
              </div>

              <textarea
                placeholder="Votre message"
                value={formData.message}
                onChange={handleInputChange('message')}
              />
              {errors.message && <p className="field-error">{errors.message}</p>}

              <div className="submit-row">
                {submitStatus && (
                  <p className={`submit-feedback submit-feedback--${submitStatus.type}`}>
                    {submitStatus.message}
                  </p>
                )}
                <button
                  type="submit"
                  className="submit-button"
                  disabled={isSubmitting}
                >
                  {isSubmitting ? 'ENVOI...' : 'ENVOYER'}
                </button>
              </div>
            </div>
          </form>
        </div>
      </section>
    </main>
  );
}

export default App;