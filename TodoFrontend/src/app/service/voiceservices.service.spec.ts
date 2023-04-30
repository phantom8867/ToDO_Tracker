import { TestBed } from '@angular/core/testing';

import { VoiceservicesService } from './voiceservices.service';

describe('VoiceservicesService', () => {
  let service: VoiceservicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VoiceservicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
