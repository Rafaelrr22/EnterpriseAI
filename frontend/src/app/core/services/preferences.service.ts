import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PreferencesService {

  private readonly showSourcesKey =
    'gef-ai-show-sources';

  get showSources(): boolean {

    const storedPreference = sessionStorage.getItem(
      this.showSourcesKey
    );

    if (storedPreference === null) {
      return true;
    }

    return storedPreference === 'true';

  }

  set showSources(value: boolean) {

    sessionStorage.setItem(
      this.showSourcesKey,
      String(value)
    );

  }

}
