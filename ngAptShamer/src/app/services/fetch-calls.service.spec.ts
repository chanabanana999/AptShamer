import { TestBed } from '@angular/core/testing';

import { FetchCallsService } from './fetch-calls.service';

describe('FetchCallsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FetchCallsService = TestBed.get(FetchCallsService);
    expect(service).toBeTruthy();
  });
});
